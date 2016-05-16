create table Patient (
  id          int not null,
  firstName   varchar(100),
  middleName  varchar(100),
  lastName    varchar(100),
  primary key (id)
);

create table PatientMailingAddress (
  id          int not null,
  patient_id  int not null,
  line1       varchar(100),
  line2       varchar(100),
  city        varchar(100),
  state       char(2),
  zip         char(10),
  primary key (id),
  foreign key (patient_id)  references Patient(id)
);

create table PatientPhoneNumber (
  id          int not null,
  patient_id  int not null,
  numbr       char(20),
  primary key (id),
  foreign key (patient_id)  references Patient(id)
);
