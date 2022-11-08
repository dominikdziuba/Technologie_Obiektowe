from data.RemoteDataProvider import *

class Group_Seven(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.group_seven_data = []
        for i in range(self.length - 1):
            self.group_seven_data.append(self.data[i][14])

    def get_atmospheric_pressure_tendency(self):
        atmospheric_pressure_tendency = {}
        for counter,station in enumerate(self.group_seven_data):
            atmospheric_pressure_tendency[counter] = station[1]
        return atmospheric_pressure_tendency

    def get_abs_atmospheric_pressure_tendency_(self):
        abs_atmospheric_pressure_tendency = {}
        for counter,station in enumerate(self.group_seven_data):
            abs_atmospheric_pressure_tendency[counter] = str(round((int(station[2:].rstrip("="))* 0.1),2)) + " hPa"
        return abs_atmospheric_pressure_tendency
