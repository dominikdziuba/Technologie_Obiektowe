class Flyweight:
    data = {}

    def add_person(self, person):
        self.data[person.first_name + person.last_name] = person
