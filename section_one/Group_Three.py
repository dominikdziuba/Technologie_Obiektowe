from data.RemoteDataProvider import *


class Group_Three(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.group_three_data = []
        for i in range(self.length - 1):
            self.group_three_data.append(self.data[i][10])

    def get_air_temperature(self):
        air_temperature = {}
        for counter, station in enumerate(self.group_three_data):
            if station[1] == 1:
                air_temperature[counter] = "-" + str(round((int(station[2:]) * 0.1),2)) + " °C"
            else:
                air_temperature[counter] = "+" + str(round((int(station[2:]) * 0.1),2)) + " °C"
        return air_temperature
