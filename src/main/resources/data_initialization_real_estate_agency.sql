
INSERT INTO clients (first_name, last_name, email, phone_number, reg_date)
VALUES ('John', 'Doe', 'john.doe@example.com', '+380984517553', '2024-01-22'),
       ('Alice', 'Smith', 'alice.smith@example.com', '+380673600765', '2024-01-22'),
       ('Bob', 'Johnson', 'bob.johnson@example.com', '+380984736256', '2024-01-22');

INSERT INTO addresses (country, region, city, street, building, apartment)
VALUES ('USA', 'California', 'Los Angeles', 'Main St', '123', 'Apt 456'),
       ('Canada', 'Ontario', 'Toronto', 'King St', '456', 'Apt 789'),
       ('UK', 'England', 'London', 'Oxford St', '789', 'Apt 638');

INSERT INTO real_estates (price, is_available, real_estate_description, real_estate_type, metrics, rooms, seller_id, address_id)
VALUES (100000.00, TRUE, 'Spacious apartment with a view', 'Apartment', '100 sqm', 3, 1, 1),
       (250000.00, TRUE, 'Cozy house with a garden', 'House', '300 sqm', 5, 2, 2),
       (50000.00, TRUE, 'Small studio in the city center', 'Apartment', '50 sqm', 1, 3, 3);

INSERT INTO tags (tag_name)
VALUES ('Luxury'),
       ('Garden'),
       ('Studio');

INSERT INTO real_estate_has_tags (tag_name, real_estate_id)
VALUES ('Luxury', 1),
       ('Garden', 2),
       ('Studio', 3);

INSERT INTO photo_links (link, real_estate_id)
VALUES ('https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.engelvoelkers.com%2Fen-ch%2Fproperty%2Fspacious-apartment-with-lake-view-in-aldesago-4707188.1551928_exp%2F&psig=AOvVaw2rbjWxWwqGMVpLm5lS-d1m&ust=1706381635624000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCMiJxo3d-4MDFQAAAAAdAAAAABAD', 1),
       ('https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.dreamstime.com%2Fstock-photo-cozy-house-beautiful-garden-sunny-day-home-exterior-image73919587&psig=AOvVaw2Z7S5aT9Ck_12CP3e2HHul&ust=1706381682241000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCMib_qLd-4MDFQAAAAAdAAAAABAD', 2),
       ('https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.zurich-hotels-now.com%2Fen%2Fproperty%2Fairhome-4s-small-studio-in-the-city-center.html&psig=AOvVaw29jPL2Y3LvTY56rrgZIrjb&ust=1706381710539000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCPj9pbPd-4MDFQAAAAAdAAAAABAD', 3);

INSERT INTO agreements (agreement_date, amount, agreement_status, duration, real_estate_id, client_id)
VALUES ('2022-01-22', 150000.00, 'Active', '12 months', 1, 1),
       ('2022-01-23', 200000.00, 'Pending', '6 months', 2, 2),
       ('2022-01-24', 100000.00, 'Completed', '24 months', 3, 3);

INSERT INTO employees (first_name, last_name, email, phone_number, employee_position, hire_date, salary)
VALUES ('Michael', 'Smith', 'michael.smith@example.com', '+380984736554', 'Manager', '2022-01-22', 50000),
       ('Emily', 'Johnson', 'emily.johnson@example.com', '+380675646773', 'Agent', '2022-01-23', 40000),
       ('Daniel', 'Brown', 'daniel.brown@example.com', '+380675722687', 'Coordinator', '2022-01-24', 45000);

INSERT INTO meetings (meeting_date_time, inquiry_date, meeting_status, real_estate_id, buyer_id, employee_id)
VALUES ('2024-03-01', '2024-02-22', 'Scheduled', 1, 2, 1),
       ('2024-04-05', '2024-02-25', 'Completed', 2, 3, 2),
       ('2024-02-10', '2024-02-18', 'Pending', 3, 1, 3);
