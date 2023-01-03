from Portfolio import *

my_port = Portfolio('Matt')
my_port.buy_stock('TSLA',55,250)
my_port.buy_stock('T',6,35)
print(my_port.get_stock_info('T'))
print(my_port)
my_port.sell_stock('TSLA',5)
my_port.update_price('T',37)
print('Total value is',my_port.get_total_value())
my_port.sell_stock('T',10)
print(my_port)