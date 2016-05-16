create table MailingAddress (
  id    int not null,
  line1 varchar(100),
  line2 varchar(100),
  city  varchar(100),
  state char(2),
  zip   char(10),
  primary key (id)
);

create table PhoneNumber (
  id int not null,
  numbr char(20),
  primary key (id)
);

create table Patient (
  id          int not null,
  firstName   varchar(100),
  middleName  varchar(100),
  lastName    varchar(100),
  primary key (id)
);

create table PatientMailingAddress (
  patient_id  int,
  address_id  int,
  primary key (patient_id, address_id),
  foreign key (patient_id)  references Patient(id),
  foreign key (address_id)  references MailingAddress(id)
);

create table PatientPhoneNumber (
  patient_id  int,
  number_id   int,
  primary key (patient_id, number_id),
  foreign key (patient_id)  references Patient(id),
  foreign key (number_id)   references PhoneNumber(id)
);