import faker from 'faker';

// TODO: export to .sql file
for (let i = 0; i < 50; i++) {
  console.log(faker.fake(`insert into data ${faker.name.firstName()} ${faker.name.lastName()}`));
}
