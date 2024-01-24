DROP TABLE IF EXISTS meetings;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS agreements;
DROP TABLE IF EXISTS photo_links;
DROP TABLE IF EXISTS real_estate_has_tags;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS real_estates;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS clients;

CREATE TABLE IF NOT EXISTS clients (
	id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone_number VARCHAR(20) UNIQUE,
    reg_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS addresses (
    id SERIAL PRIMARY KEY,
    country VARCHAR(50) NOT NULL,
    region VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    street VARCHAR(100) NOT NULL,
    building VARCHAR(100),
    apartment VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS real_estates (
	id SERIAL PRIMARY KEY,
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN NOT NULL,
    real_estate_description VARCHAR(150) NULL,
    real_estate_type VARCHAR(50) NOT NULL,
    metrics VARCHAR(50) NOT NULL,
    rooms INT NOT NULL,
    seller_id BIGINT UNSIGNED NOT NULL,
    address_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY(seller_id) REFERENCES clients(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY(address_id) REFERENCES addresses(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS tags (
    tag_name VARCHAR(100) UNIQUE PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS real_estate_has_tags (
	tag_name VARCHAR(100) NOT NULL,
	real_estate_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY(real_estate_id) REFERENCES real_estates(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY(tag_name) REFERENCES tags(tag_name) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS photo_links (
    link VARCHAR(200) UNIQUE PRIMARY KEY NOT NULL,
    real_estate_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY(real_estate_id) REFERENCES real_estates(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS agreements (
    id SERIAL PRIMARY KEY, 
    agreement_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    agreement_status VARCHAR(50) NOT NULL,
    duration VARCHAR(100),
    real_estate_id BIGINT UNSIGNED NOT NULL,
    client_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY(real_estate_id) REFERENCES real_estates(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY(client_id) REFERENCES clients(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY, 
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    position VARCHAR(50),
    hire_date DATE NOT NULL,
    salary BIGINT UNSIGNED
);

CREATE TABLE IF NOT EXISTS meetings (
    id SERIAL PRIMARY KEY,
    meeting_date_time DATE NOT NULL,
    inquiry_date DATE NOT NULL,
    meeting_status VARCHAR(100) NOT NULL,
    real_estate_id BIGINT UNSIGNED NOT NULL,
    buyer_id BIGINT UNSIGNED NOT NULL,
    employee_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY(real_estate_id) REFERENCES real_estates(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY(buyer_id) REFERENCES clients(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY(employee_id) REFERENCES employees(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
