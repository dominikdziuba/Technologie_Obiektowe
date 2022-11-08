from section_zero.Section_Zero import *
from data.Data import *
from section_one.Section_One import *


class Extractor(Section_Zero, Section_One):
    def __init__(self, parsed_list):
        Section_Zero.__init__(self)
        Section_One.__init__(self)
        self.parsed_list = parsed_list

    def print_data(self):
        for key in self.parsed_list:
            print("\n--Pomiar--")
            print(self.dates[key])
            print("wskaźnik wiatru: " + all_data["wind_index"][self.wind_index[key]])

            print("międzynarodowy identyfikator stacji lądowej: \nnr blokowy: " + self.station_ID[key][0] +
                  ", indywidualny nr stacji: " + self.station_ID[key][1])

            print("wskaźnik grupy opadowej: " + all_data["rain_group_index"][
                self.group_one.get_rain_group_index()[key]])
            print("typ stacji: " + all_data["station_type"][self.group_one.get_station_type()[key]])
            print("wysokość względna podstawy najniższych chmur: " + all_data["relative_height"][
                self.group_one.get_relative_height()[key]])
            print("Widzialność w kierunku poziomym: " + all_data["horizontal_visibility"][
                self.group_one.get_horizontal_visibility()[key]] )

            print("Wielkość zachmurzenia ogólnego: " + all_data["general_cloud_index"][
                self.group_two.get_general_cloud_index()[key]])
            print("średni kierunek wiatru rzeczywsitego: " + all_data["average_wind_direction"][
                self.group_two.get_average_wind_direction()[key]])
            print("prędkość wiatru w węzłach: " + self.group_two.get_wind_speed()[key])

            print("Temperatura powietrza: " + self.group_three.get_air_temperature()[key])

            print("Temperatura punktu rosy:" + self.group_four.get_dewpoint_temperature()[key])

            print("Ciśnienie atmosferyczne na poziomie stacji:" + self.group_five.get_pressure_at_station()[key])

            print("Ciśnienie atmosferyczne na poziomie morza:" + self.group_six.get_pressure_at_sealevel()[key])

            print("Charakterystyka tendencji ciśnienienia:" + all_data["atmospheric_pressure_tendency"][
                self.group_seven.get_atmospheric_pressure_tendency()[key]])
            print("Bezwzględna wartość tendencji ciśnienia:" +
                  self.group_seven.get_abs_atmospheric_pressure_tendency_()[key])

            self.precipation_sum_printer(key)
            self.now_past_weather_printer(key)
            self.all_clouds_group_printer(key)

    def precipation_sum_printer(self, key):
        if key in self.rest_groups.get_rest_data("6").keys():
            sum = self.rest_groups.get_rest_data("6")[key][1:4]
            time = self.rest_groups.get_rest_data("6")[key][4]
            if sum in list(all_data["precipation_sum"].keys()):
                print("Suma opadów: " + all_data["precipation_sum"][sum])
            else:
                print("Suma opadów: " + sum.lstrip("0") + " mm")
            print("Czas trwania okresu opadu: " + all_data["precipation_period"][time] + "\n")

    def now_past_weather_printer(self, key):
        if key in self.rest_groups.get_rest_data("7").keys():
            now_weather = self.rest_groups.get_rest_data("7")[key][1:3]
            print("Pogoda bieżąca:" + all_data["weather_now"][now_weather])
            past_weather = self.rest_groups.get_rest_data("7")[key][3]
            past_weather2 = self.rest_groups.get_rest_data("7")[key][4]
            print("Pogoda ubiegła:" + all_data["weather_in_past"][past_weather], end="")
            if past_weather != past_weather2:
                print(all_data["weather_in_past"][past_weather2])
            print("\n")

    def all_clouds_group_printer(self, key):
        if key in self.rest_groups.get_rest_data("8").keys():
            cloud_size = self.rest_groups.get_rest_data("8")[key][1]
            print("Opis chmur:")
            print("Wielkość zachmurzenia (oktanty): " + cloud_size)
            first = self.rest_groups.get_rest_data("8")[key][2]
            second = self.rest_groups.get_rest_data("8")[key][3]
            third = self.rest_groups.get_rest_data("8")[key][4]
            print("Chmury grupy Stratocumulus: " + all_data["first_clouds_group"][first])
            print("Chmury grupy Altocumulus: " + all_data["second_clouds_group"][second])
            print("Chmury grupy Cirrus: " + all_data["third_clouds_group"][third])