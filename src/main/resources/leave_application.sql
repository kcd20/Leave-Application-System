-- DROP DATABASE IF EXISTS leave_app;
                
-- CREATE DATABASE IF NOT EXISTS leave_app;

USE leave_app;

INSERT INTO leave_app.role (role_id, role_name, description, annual_leave_number, medical_leave_number) VALUES ("admin", "Administrator", "System administrators.", 14, 60);
INSERT INTO leave_app.role (role_id, role_name, description, annual_leave_number, medical_leave_number) VALUES ("staff", "Staff", "Regular staffs.", 18, 60);
INSERT INTO leave_app.role (role_id, role_name, description, annual_leave_number, medical_leave_number) VALUES ("manager", "Manager", "Managers of subordinates.", 18, 60);

INSERT INTO leave_app.employee (name, manager_id, medical_leave_remaining, annual_leave_remaining, compensation_leave_remaining) VALUES("dilbert", "pointy", 10, 4, 60);
INSERT INTO leave_app.employee (name, manager_id, medical_leave_remaining, annual_leave_remaining, compensation_leave_remaining) VALUES("dogbert", "pointy", 5, 0, 60);
INSERT INTO leave_app.employee (name, manager_id, medical_leave_remaining, annual_leave_remaining, compensation_leave_remaining) VALUES("catbert", "pointy", 15, 10, 60);
INSERT INTO leave_app.employee (name, manager_id, medical_leave_remaining, annual_leave_remaining, compensation_leave_remaining) VALUES("tintin", "haddock", 12, 5, 60);
INSERT INTO leave_app.employee (name, manager_id, medical_leave_remaining, annual_leave_remaining, compensation_leave_remaining) VALUES("snowy", "haddock", 7, 20, 60);
INSERT INTO leave_app.employee (name, manager_id, medical_leave_remaining, annual_leave_remaining, compensation_leave_remaining) VALUES("cuthbert", "haddock", 10, 4, 60);
INSERT INTO leave_app.employee (name, manager_id, medical_leave_remaining, annual_leave_remaining, compensation_leave_remaining) VALUES("pointy", "pointy", 0, 0, 60);
INSERT INTO leave_app.employee (name, manager_id, medical_leave_remaining, annual_leave_remaining, compensation_leave_remaining) VALUES("haddock", "haddock", 1, 40, 60);

INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("dilbert", "Dilbert", "dilbert", "dilbert@example.com", "dilbert");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("catbert", "Catbert", "catbert", "catbert@example.com", "catbert");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("dogbert", "Dogbert", "dogbert", "dogbert@example.com", "dogbert");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("pointy", "Pointy", "pointy", "pointy@example.com", "pointy");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("tintin", "Tintin", "tintin", "tintin@example.com", "tintin");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("haddock", "Haddock", "haddock", "haddock@example.com", "haddock");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("cuthbert", "Cuthbert", "cuthbert", "cuthbert@example.com", "cuthbert");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("snowy", "Snowy", "snowy", "snowy@example.com", "snowy");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("asterix", "Asterix", "asterix", "asterix@example.com", "asterix");
INSERT INTO leave_app.user(user_id, user_name, password, email_address, employee_id) VALUES("obelix", "Obelix", "obelix", "obelix@example.com", "obelix");

INSERT INTO leave_app.userrole(role_id, user_id) VALUES("staff", "dilbert");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("staff", "catbert");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("staff", "dogbert");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("staff", "tintin");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("staff", "cuthbert");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("staff", "snowy");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("manager", "pointy");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("manager", "haddock");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("admin", "asterix");
INSERT INTO leave_app.userrole(role_id, user_id) VALUES("admin", "obelix");

INSERT INTO leave_app.application(application_id, from_date, to_date, leave_type, reason, contact_phone, work_dissemination, employee_id, status) VALUES(101, '2020-01-01', '2020-01-10', 'ANNUAL_LEAVE', 'health check up', '123456789', 'NA', 'dilbert', 'REJECTED');
INSERT INTO leave_app.application(application_id, from_date, to_date, leave_type, reason, contact_phone, work_dissemination, employee_id, status) VALUES(114, '2020-07-07', '2020-07-08', 'MEDICAL_LEAVE', 'health check up', '123456789', 'NA', 'dilbert', 'APPROVED');
INSERT INTO leave_app.application(application_id, from_date, to_date, leave_type, reason, contact_phone, work_dissemination, employee_id, status) VALUES(150, '2021-08-16', '2021-08-20', 'ANNUAL_LEAVE', 'health check up', '123456789', 'you decide boss', 'dilbert', 'APPLIED');
INSERT INTO leave_app.application(application_id, from_date, to_date, leave_type, reason, contact_phone, work_dissemination, employee_id, status) VALUES(80, '2020-08-17', '2020-08-17', 'ANNUAL_LEAVE', '1234586', '', 'NA', 'dogbert', 'UPDATED');
INSERT INTO leave_app.application(application_id, from_date, to_date, leave_type, reason, contact_phone, work_dissemination, employee_id, status) VALUES(81, '2020-08-17', '2020-08-17', 'ANNUAL_LEAVE', '1234578', '', 'NA', 'catbert', 'UPDATED');
INSERT INTO leave_app.application(application_id, from_date, to_date, leave_type, reason, contact_phone, work_dissemination, employee_id, status) VALUES(102, '2021-05-01', '2021-05-11', 'COMPENSATION_LEAVE', 'break', '123456789', '', 'cuthbert', 'APPLIED');
INSERT INTO leave_app.application(application_id, from_date, to_date, leave_type, reason, contact_phone, work_dissemination, employee_id, status) VALUES(501, '2021-12-01', '2022-01-01', 'COMPENSATION_LEAVE', 'investigation', '100011000', '', 'tintin', 'UPDATED');

INSERT INTO leave_app.overtime(overtime_id, date, hours, status, employee_id) VALUES(1, '2021-12-01', 1, 'APPLIED', 'dilbert');
INSERT INTO leave_app.overtime(overtime_id, date, hours, status, employee_id) VALUES(2, '2021-12-02', 2, 'APPLIED', 'dilbert');
INSERT INTO leave_app.overtime(overtime_id, date, hours, status, employee_id) VALUES(3, '2021-12-03', 1, 'APPLIED', 'dilbert');

INSERT INTO leave_app.compensation_claim(claim_id, date_applied, employee_id, status) VALUES(1, '2021-12-20', 'dilbert', 'APPLIED');

INSERT INTO leave_app.compensation_claim_overtimes(compensation_claim_claim_id, overtimes_overtime_id) VALUES(1, 1);
INSERT INTO leave_app.compensation_claim_overtimes(compensation_claim_claim_id, overtimes_overtime_id) VALUES(1, 2);
INSERT INTO leave_app.compensation_claim_overtimes(compensation_claim_claim_id, overtimes_overtime_id) VALUES(1, 3);
