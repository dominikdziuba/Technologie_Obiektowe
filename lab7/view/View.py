from mediator import *
from flyweight import *
from person import Person

class View():

    def __init__(self):
        self.factory = FlyweightFactory()
        self.real_subject = RealSubject()
        self.proxy = Proxy(self.real_subject)

    def input_data(self):
        first_name = input("Podaj imię: ")
        last_name = input("Podaj nazwisko: ")
        latitude = input("Podaj szerokość geograficzną: ")
        longitude = input("Podaj długość geograficzną: ")

        person = Person(first_name, last_name, latitude, longitude)
        flyweight = self.factory.get_flyweight(person)

        self.proxy.add_person(person)

        self.proxy.save_data("data.txt")