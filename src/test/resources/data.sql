INSERT INTO role(id, name) VALUES(1, "ROLE_ADMIN") ON DUPLICATE KEY UPDATE id=1, name="ROLE_ADMIN";
INSERT INTO role(id, name) VALUES(2, "ROLE_USER") ON DUPLICATE KEY UPDATE id=2, name="ROLE_USER";