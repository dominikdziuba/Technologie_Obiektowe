from flyweight import Flyweight


class FlyweightFactory:
    flyweights = {}

    def get_flyweight(self, person):
        key = person.first_name + person.last_name
        if key not in self.flyweights:
            self.flyweights[key] = Flyweight()
        return self.flyweights[key]
