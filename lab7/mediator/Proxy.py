class Proxy:
  def __init__(self, real_subject):
    self.real_subject = real_subject

  def add_person(self, person):
    self.real_subject.add_person(person)

  def save_data(self, filename):
    with open(filename, "a") as file:
      for name, (latitude, longitude) in self.real_subject.data.items():
        file.write(name + " " + str(latitude) + " " + str(longitude) + "\n")
