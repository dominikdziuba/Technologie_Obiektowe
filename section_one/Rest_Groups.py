from data.RemoteDataProvider import *

class Rest_Groups(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)

    def get_rest_data(self, num):
        dictionary = {}
        for i in range(self.length - 1):
            for el in self.data[i][14:]:
                if el.startswith(num):
                    dictionary[i] = el
        return dictionary
