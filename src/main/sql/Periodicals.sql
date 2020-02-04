use projects;

CREATE TABLE periodicals (
   pid int(11) NOT NULL AUTO_INCREMENT,
   title varchar(128) NOT NULL,
   price float NOT NULL,
  PRIMARY KEY (pid),
  UNIQUE KEY pid_UNIQUE (pid),
  UNIQUE KEY title_UNIQUE (title)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

