INSERT INTO address (building_management_number, administrative_building_code, administrative_building_name, apartment_house_separator, building_center_pointx, building_center_pointy, building_count, building_number, building_side_number, building_use_classification, city_eng_name, city_name, country_eng_name, country_name, entrance_pointx, entrance_pointy, ground_floor_number, is_living, is_underground, road_eng_name, road_name, road_name_code, town_code, town_eng_name, town_name, town_separator, underground_floor_number, zip_code) VALUES ('2818510200200690000123263', '2818575000', '선학동', '0', 928447.409277, 1936542.944271, 5, 52, 0, '주택', 'Incheon', '인천광역시', 'Yeonsu-gu', '연수구', 928454.596747, 1936530.112916, 1, '1', '0', 'Biryu-daero 519beon-gil', '비류대로519번길', '281854256100', '2818510200', 'Seonhak-dong', '선학동', 1, 0, '21911');
INSERT INTO address (building_management_number, administrative_building_code, administrative_building_name, apartment_house_separator, building_center_pointx, building_center_pointy, building_count, building_number, building_side_number, building_use_classification, city_eng_name, city_name, country_eng_name, country_name, entrance_pointx, entrance_pointy, ground_floor_number, is_living, is_underground, road_eng_name, road_name, road_name_code, town_code, town_eng_name, town_name, town_separator, underground_floor_number, zip_code) VALUES ('2818510200200690000205284', '2818575000', '선학동', '0', 928440.288084, 1936536.662753, 5, 52, 0, '주택', 'Incheon', '인천광역시', 'Yeonsu-gu', '연수구', 928454.596747, 1936530.112916, 1, '1', '0', 'Biryu-daero 519beon-gil', '비류대로519번길', '281854256100', '2818510200', 'Seonhak-dong', '선학동', 1, 0, '21911');
INSERT INTO address (building_management_number, administrative_building_code, administrative_building_name, apartment_house_separator, building_center_pointx, building_center_pointy, building_count, building_number, building_side_number, building_use_classification, city_eng_name, city_name, country_eng_name, country_name, entrance_pointx, entrance_pointy, ground_floor_number, is_living, is_underground, road_eng_name, road_name, road_name_code, town_code, town_eng_name, town_name, town_separator, underground_floor_number, zip_code) VALUES ('2818510200200690001074812', '2818575000', '선학동', '0', 928454.247791, 1936533.758249, 5, 52, 0, '주택', 'Incheon', '인천광역시', 'Yeonsu-gu', '연수구', 928454.596747, 1936530.112916, 1, '1', '0', 'Biryu-daero 519beon-gil', '비류대로519번길', '281854256100', '2818510200', 'Seonhak-dong', '선학동', 1, 0, '21911');

INSERT INTO shops (shop_id, name, status, delivery_type, operating_time, min_order_amount, business_number, owner_id, introduction, guide, tel_number, BUILDING_MANAGEMENT_NUMBER, created_date, modified_date) VALUES (1, 'shop 1', 'INACTIVE', 'RIDER', '06:00 ~ 23:00', 10000, '000000-000-000000', 1, 'introduction', 'guide', '000-0000-0000', '2818510200200690000123263', '2020-01-01T00:00:00', '2020-01-01T00:00:00');

INSERT INTO menu_groups (menu_group_id, shop_id, name, description, priority, representative, status) VALUES (1, 1, 'menu group 1', 'menu group 1 description', 0, true, 'INACTIVE');

INSERT INTO menus (menu_id, menu_group_id, name, description, image_url, priority, status) VALUES (1, 1, 'menu 1', 'menu 1 description', '', 0, 'INACTIVE');

INSERT INTO option_groups (option_group_id, name, basic, selectable, priority, status) VALUES (1, 'option group 1(기본)', true, false, 0, 'INACTIVE');
INSERT INTO option_groups (option_group_id, name, basic, selectable, priority, status) VALUES (2, 'option group 2', false, true, 0, 'INACTIVE');

INSERT INTO MENU_OPTION_GROUP (MENU_OPTION_GROUP_ID, MENU_ID, OPTION_GROUP_ID) VALUES (1, 1, 1);
INSERT INTO MENU_OPTION_GROUP (MENU_OPTION_GROUP_ID, MENU_ID, OPTION_GROUP_ID) VALUES (2, 1, 2);

INSERT INTO options (option_id, option_group_id, name, price) VALUES (1, 1, 'option 1-1', 15000);
INSERT INTO options (option_id, option_group_id, name, price) VALUES (2, 1, 'option 1-2', 20000);
INSERT INTO options (option_id, option_group_id, name, price) VALUES (3, 2, 'option 2-1', 1000);
INSERT INTO options (option_id, option_group_id, name, price) VALUES (4, 2, 'option 2-2', 2000);

INSERT INTO members (id, email, username, password, is_active, email_verified, created_date, modified_date) VALUES (1, 'member1@test.com', 'member1', '$2a$10$6E0DogVVDwOl7XRsMDehbecL3hfYbY5AtIKF7duGEqPVBGA7eFkLS', true, true, '2020-01-01T00:00:00', '2020-01-01T00:00:00');

insert into owners (id, created_date, modified_date, is_active, email, password, username) values (1, '2020-09-09 01:35:38', '2020-09-09 01:35:38', TRUE,	'owner1@test.com', '$2a$10$yZKs44NW2.AWCxJfsjKqsO0lLnjvvhSXO7v/sWrB.fOByZUQxoOzO', 'owner1');
insert into owners (id, created_date, modified_date, is_active, email, password, username) values (2, '2020-09-09 01:35:38', '2020-09-09 01:35:38', TRUE,	'owner2@test.com', '$2a$10$yZKs44NW2.AWCxJfsjKqsO0lLnjvvhSXO7v/sWrB.fOByZUQxoOzO', 'owner2');

--INSERT INTO orders (order_id, user_id, shop_id, ordered_time, status) VALUES (1, 1, 1, '2020-01-01T00:00:00', 'ORDERED');
--
--INSERT INTO order_menu_items (order_menu_item_id, order_id, menu_id, name, count) VALUES (1, 1, 1, 'order 1', 2);
--
--INSERT INTO order_option_groups (order_option_group_id, order_menu_item_id, name) VALUES (1, 1, 'option group 1(기본)');
--
--INSERT INTO order_options (order_option_id, order_option_group_id, name, price) VALUES (1, 1, 'option 1-1', 15000);