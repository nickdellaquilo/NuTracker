Create table users 
	(username		varchar(10),
	password		varchar(100),
	gender		varchar(6),
	age			numeric(3, 0),
	weight		numeric(3, 0),
	height		numeric(3, 0),
	activity		varchar(10),
	primary key (username)
);

Create table consumption
	(username		varchar(10),
	foodID		varchar(30),
	logtime		DATETIME, 
	logID			numeric(10, 0),
	primary key (logID),
	foreign key (username) references users(username) 
on delete cascade
);



