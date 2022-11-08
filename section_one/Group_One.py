from data.RemoteDataProvider import *

class Group_One(RemoteDataProvider):
    def __init__(self):
        self.group_one_data = []
        RemoteDataProvider.__init__(self)
        for i in range(self.length - 1):
            self.group_one_data.append(self.data[i][8])

    def get_rain_group_index(self):
        rain_group_index = {}
        for counter,station in enumerate(self.group_one_data):
            rain_group_index[counter] = station[0]
        return rain_group_index

    def get_station_type(self):
        station_type = {}
        for counter,station in enumerate(self.group_one_data):
            station_type[counter] = station[1]
        return station_type

    def get_relative_height(self):
        relative_height = {}
        for counter,station in enumerate(self.group_one_data):
            relative_height[counter] = station[2]
        return relative_height

    def get_horizontal_visibility(self):
        horizontal_visibility = {}
        for counter,station in enumerate(self.group_one_data):
            horizontal_visibility[counter] = station[3:]
        return horizontal_visibility