from data.RemoteDataProvider import *

class Group_Four(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.group_four_data = []
        for i in range(self.length - 1):
            self.group_four_data.append(self.data[i][11])

    def get_dewpoint_temperature(self):
        dewpoint_temperature = {}
        for counter, station in enumerate(self.group_four_data):
            if station[1] == 1:
                dewpoint_temperature[counter] = "- " + str(round((int(station[2:]) * 0.1),2)) + " °C"
            else:
                dewpoint_temperature[counter] = "+ " + str(round((int(station[2:]) * 0.1),2)) + " °C"

        return dewpoint_temperature
