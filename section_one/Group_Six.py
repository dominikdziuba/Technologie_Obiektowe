from data.RemoteDataProvider import *


class Group_Six(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.group_six_data = []
        for i in range(self.length - 1):
            self.group_six_data.append(self.data[i][13])

    def get_pressure_at_sealevel(self):
        pressure_at_sealevel = {}
        for counter, station in enumerate(self.group_six_data):
            pressure_at_sealevel[counter] = str(round((int(station[1:])* 0.1),2)) + " hPa"
        return pressure_at_sealevel
