INSERT INTO shops (name, status, operating_time, min_order_amount, created_date, modified_date) VALUES ('shop 1', 'INACTIVE', '06:00 ~ 23:00', 10000, '2020-01-01T00:00:00', '2020-01-01T00:00:00');

INSERT INTO menu_groups (shop_id, name, description, priority, representative, status) VALUES (1, 'menu group 1', 'menu group 1 description', 0, true, 'INACTIVE');

INSERT INTO menus (menu_group_id, name, description, image_url, priority, status) VALUES (1, 'menu 1', 'menu 1 description', '', 0, 'INACTIVE');

INSERT INTO option_groups (menu_id, name, basic, selectable, priority, status) VALUES (1, 'option group 1(기본)', true, false, 0, 'INACTIVE');
INSERT INTO option_groups (menu_id, name, basic, selectable, priority, status) VALUES (1, 'option group 2', false, true, 0, 'INACTIVE');

INSERT INTO options (option_group_id, name, price) VALUES (1, 'option 1-1', 15000);
INSERT INTO options (option_group_id, name, price) VALUES (1, 'option 1-2', 20000);
INSERT INTO options (option_group_id, name, price) VALUES (2, 'option 2-1', 1000);
INSERT INTO options (option_group_id, name, price) VALUES (2, 'option 2-2', 2000);