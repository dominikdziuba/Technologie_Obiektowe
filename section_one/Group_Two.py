from data.RemoteDataProvider import *


class Group_Two(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.group_two_data = []
        for i in range(self.length - 1):
            self.group_two_data.append(self.data[i][9])

    def get_general_cloud_index(self):
        general_cloud_index = {}
        for counter, station in enumerate(self.group_two_data):
            general_cloud_index[counter] = station[0]
        return general_cloud_index

    def get_average_wind_direction(self):
        average_wind_direction = {}
        for counter, station in enumerate(self.group_two_data):
            average_wind_direction[counter] = station[1:3]
        return average_wind_direction

    def get_wind_speed(self):
        wind_speed = {}
        for counter, station in enumerate(self.group_two_data):
            wind_speed[counter] = station[-1]
        return wind_speed

