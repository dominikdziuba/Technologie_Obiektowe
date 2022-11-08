from data.RemoteDataProvider import *

class WindIndex(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.winds = []
        for i in range(self.length - 1):
            self.winds.append(self.data[i][6])


    def get_wind_index(self):
        wind_indexes = {}
        for counter, station in enumerate(self.winds):
            wind_indexes[counter] = station[-1]
        return wind_indexes
