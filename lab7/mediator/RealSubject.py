class RealSubject:
  def __init__(self):
    self.data = {}

  def add_person(self, person):
    person.first_name = self.standardize_name(person.first_name)
    person.last_name = self.standardize_name(person.last_name)
    self.data[person.first_name + " " + person.last_name] = (person.latitude, person.longitude)

  def standardize_name(self, name):
    return name[0].upper() + name[1:].lower()
