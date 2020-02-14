create table Planet (
	ID varchar(50) NOT NULL,
	Name varchar(50) NOT NULL,
	Primary Key(ID)
);

create table Route (
	ID bigint NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	OriginID varchar(50) NOT NULL,
	DestinationID varchar(50) NOT NULL,
	Distance float NOT NULL,

	Primary Key(ID)
);



