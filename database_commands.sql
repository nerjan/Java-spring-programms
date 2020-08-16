create database db_przepisy;
create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
grant all on db_przepisy.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database