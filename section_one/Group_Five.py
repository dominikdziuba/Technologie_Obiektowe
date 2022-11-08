from data.RemoteDataProvider import *

class Group_Five(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.group_five_data = []
        for i in range(self.length - 1):
            self.group_five_data.append(self.data[i][12])

    def get_pressure_at_station(self):
        pressure_at_station = {}
        for counter,station in enumerate(self.group_five_data):
                pressure_at_station[counter] = str(round((int(station[1:])* 0.1),2)) + " hPa"
        return pressure_at_station