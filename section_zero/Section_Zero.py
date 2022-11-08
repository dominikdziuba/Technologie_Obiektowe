from section_zero.Dates import *
from section_zero.WindIndex import *
from section_zero.StationID import *

class Section_Zero():
    def __init__(self):
        self.dates = Dates().return_date()
        self.wind_index = WindIndex().get_wind_index()
        self.station_ID = StationID().return_international_land_station_indicator()
