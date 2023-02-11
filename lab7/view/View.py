from mediator import *
from flyweight import *
from person import Person

class View():
    factory = FlyweightFactory()
    real_subject = RealSubject()
    proxy = Proxy(real_subject)

    first_name = input("Podaj imię: ")
    last_name = input("Podaj nazwisko: ")
    latitude = input("Podaj szerokość geograficzną: ")
    longitude = input("Podaj długość geograficzną: ")

    person = Person(first_name, last_name, latitude, longitude)
    flyweight = factory.get_flyweight(person)

    proxy.add_person(person)

    proxy.save_data("data.txt")