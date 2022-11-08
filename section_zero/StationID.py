from data.RemoteDataProvider import *

class StationID(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.IDs = []
        for i in range(self.length - 1):
            self.IDs.append(self.data[i][7])

    def return_international_land_station_indicator(self):
        station_ID = {}
        for counter, station in enumerate(self.IDs):
            station_ID[counter] = [station[:2], station[2:]]
        return station_ID
