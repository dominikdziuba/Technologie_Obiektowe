from section_zero.Dates import Dates
from datetime import datetime
from Extractor.Extractor import Extractor

class View():
    def __init__(self):
        d = Dates()
        self.dates = d.return_date()
        self.dictionary = {}

    def parser(self, b_date, e_date):
        dates_list = []
        for key, value in self.dates.items():
            if b_date < value < e_date:
                dates_list.append(key)
        return dates_list

    def show(self):
        try:
            print("Poczatek przedzialu użytkownika:")

            b_day = int(input("Dzien: "))
            b_month = int(input("Miesiac: "))
            b_year = int(input("Rok: "))
            b_hour = int(input("Godzina: "))
            b_minute = int(input("Minuta: "))


            print("Koniec przedzialu użytkownika:")
            e_day = int(input("Dzien: "))
            e_month = int(input("Miesiac: "))
            e_year = int(input("Rok: "))
            e_hour = int(input("Godzina: "))
            e_minute = int(input("Minuta: "))

            b = datetime(year =b_year, month=b_month, day=b_day, hour=b_hour, minute=b_minute)
            e = datetime(year = e_year, month = e_month, day = e_day, hour=e_hour, minute=e_minute)

            if e < b:
                temp = datetime
                temp = b
                b=e
                e=temp
            parsed_keys_list = self.parser(b, e)

            ex = Extractor(parsed_keys_list)
            ex.print_data()

        except ValueError:
            print("Nieprawidłowy zakres dat, spróbuj ponownie.")


