from datetime import datetime
from data import *

class Dates(RemoteDataProvider):
    def __init__(self):
        RemoteDataProvider.__init__(self)
        self.dates_string = []
        for i in range(self.length - 1):
            self.dates_string.append(self.data[i][0:5])

    def return_date(self):
        dates = {}
        for counter,station in enumerate(self.dates_string):
            dates[counter] = datetime(year = int(station[0]), month =  int(station[1]), day =  int(station[2]), hour =  int(station[3]), minute = int(station[4]))
        return dates
