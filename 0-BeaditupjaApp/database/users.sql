-- By default MySql does not allow connecting from another machine as the one where the server is installed. 
-- You can enable remote access during installation ( see the next chapter ) or later using the instructions below.
-- On the server type in the command prompt or terminal mysql -u root -p <root_password> The mysql console should start.
-- List the databases using show databases
-- View the configured grants using select * from db;
-- Enable remote access for a user foo using GRANT ALL ON foo.* TO bar@'202.54.10.20' IDENTIFIED BY 'PASSWORD'; 
-- Here you have to edit the user ( put your user instead of foo ), the IP of the client machine and the root password.


GRANT ALL PRIVILEGES ON beaditupja.* TO 'admin'@'localhost'
IDENTIFIED BY 'admin';