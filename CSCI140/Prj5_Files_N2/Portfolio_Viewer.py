import sys
from Portfolio import *
from copy import deepcopy
from PyQt5.QtGui import QPainter, QColor, QFont, QPen, QBrush
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import *
from random import randint


class Portfolio_Viewer(QWidget):

  def __init__(self, portfolio):
    super().__init__()
  
    self.__portfolio = portfolio
    self.__symbols = self.__portfolio.symbol_map.copy()
    self.setWindowTitle(portfolio.get_name() + "'s Portfolio")
    self.setGeometry(300, 300,600,600)
    self.add = QPushButton('Buy stock')
    self.add.setDefault(False)
    self.add.setAutoDefault(False)
    
    self.remove = QPushButton('Sell stock')
    
    self.remove.setDefault(False)
    self.remove.setAutoDefault(False)
    self.change = QPushButton('Update stock price')
    self.change.setDefault(False)
    self.change.setAutoDefault(False)
	#List on left side
        
    #Window with grid layout for pushbuttons
    self.my_win=  QDialog()
    self.win_panel = QListWidget(self.my_win)
    self.win_panel.itemClicked.connect(self.display_info)
    windowLayout = QGridLayout()
    windowLayout.addWidget(self.win_panel, 0,1)
    windowLayout.addWidget(self.add,1,1)
    windowLayout.addWidget(self.change, 2,1)
    windowLayout.addWidget(self.remove,3,1)
    self.my_win.setLayout(windowLayout)
    layout = QHBoxLayout()
    #Window with panel for real-time display and summary info
    self.my_rwin=  QDialog()
    
    self.panel = QTextBrowser(self.my_rwin)
    self.panel.setGeometry(10,10,200,200)
    self.disp_panel = QTextBrowser(self.my_rwin)
    self.disp_panel.setText('Total Portfolio Value:\n\n' + str(self.__portfolio.get_total_value()))
    self.disp_panel.setGeometry(250,250,100,50)
   
    rwindowLayout = QVBoxLayout()
    rwindowLayout.addWidget(self.panel)
    rwindowLayout.addWidget(self.disp_panel)
    self.my_rwin.setLayout(rwindowLayout)
    layout.addWidget(self.my_win)
    layout.addWidget(self.my_rwin)
    self.setLayout(layout)
    self.__setup_dialogs()
    self.add.clicked.connect(lambda: self.buy.show())
    self.remove.clicked.connect(lambda: self.sell.show())
    self.change.clicked.connect(lambda: self.update.show())
    self.show()
  

  def __setup_dialogs(self):
    self.buy = QDialog(self)
    master_layout = QVBoxLayout()
    self.buy.setWindowTitle('Purchase stock')
    self.buy.setLayout(master_layout)
    self.symbol_choice = QLineEdit()
    self.share_choice = QLineEdit()
    self.price = QLineEdit()
    master_layout.addWidget(QLabel('Enter stock symbol:'))
    master_layout.addWidget(self.symbol_choice)
    master_layout.addWidget(QLabel('Enter number of shares to purchase: '))
    master_layout.addWidget(self.share_choice)
    master_layout.addWidget(QLabel('Current share price: '))
    master_layout.addWidget(self.price)
    self.price.setText('100')
    OK = QPushButton('Purchase') #may need to add validators to check
    OK.setDefault(False)
    OK.setAutoDefault(False) #add reset
    OK.clicked.connect(self.__buy_stock)
    cancel = QPushButton('Cancel')
    cancel.setDefault(False)
    cancel.setAutoDefault(False)
    cancel.clicked.connect(lambda: self.buy.close())
    button_w = QWidget()
    l2 = QGridLayout()
    button_w.setLayout(l2)
    l2.addWidget(OK, 0,0)
    l2.addWidget(cancel, 0,2)
    master_layout.addWidget(button_w)
    self.buy.setGeometry(400, 400,150,150)
    #sell
    self.sell = QDialog(self)
    master_layout_sell = QVBoxLayout()
    self.sell.setWindowTitle('Sell stock')
    self.sell.setLayout(master_layout_sell)
    self.sell_choice = QComboBox(self.sell)
    self.sell_choice.addItems(self.__portfolio.get_symbols())
    self.share_sell_choice = QLineEdit()
    master_layout_sell.addWidget(QLabel('Select stock symbol:'))
    master_layout_sell.addWidget(self.sell_choice)
    master_layout_sell.addWidget(QLabel('Enter number of shares to sell: '))
    master_layout_sell.addWidget(self.share_sell_choice)
    if self.sell_choice.currentText() != '':
        cur_price = self.price.setText(self.__portfolio.get_stock_info(self.sell_choice.currentText())[2])
        master_layout_sell.addWidget(QLabel('Current share price: '+str(cur_price)))
    OK_sell = QPushButton('Sell') #may need to add validators to check
    OK_sell.setDefault(False)
    OK_sell.setAutoDefault(False) #add reset
    OK_sell.clicked.connect(self.__sell_stock)
    cancel_sell = QPushButton('Cancel')
    cancel_sell.setDefault(False)
    cancel_sell.setAutoDefault(False)
    cancel_sell.clicked.connect(lambda: self.sell.close())
    button_w_sell = QWidget()
    l2_sell = QGridLayout()
    button_w_sell.setLayout(l2_sell)
    l2_sell.addWidget(OK_sell, 0,0)
    l2_sell.addWidget(cancel_sell, 0,2)
    master_layout_sell.addWidget(button_w_sell)
    self.sell.setGeometry(400, 400,150,150)
    #update
    self.update = QDialog(self)
    master_layout_update = QVBoxLayout()
    self.update.setWindowTitle('Update stock price')
    self.update.setLayout(master_layout_update)
    self.update_choice = QComboBox(self.update)
    self.update_choice.addItems(self.__portfolio.get_symbols())
    self.update_price_choice = QLineEdit()
    master_layout_update.addWidget(QLabel('Select stock symbol:'))
    master_layout_update.addWidget(self.update_choice)
    master_layout_update.addWidget(QLabel('Enter new price: '))
    master_layout_update.addWidget(self.update_price_choice)
    OK_update = QPushButton('Update') #may need to add validators to check
    OK_update.setDefault(False)
    OK_update.setAutoDefault(False) #add reset
    OK_update.clicked.connect(self.__update_price)
    cancel_update = QPushButton('Cancel')
    cancel_update.setDefault(False)
    cancel_update.setAutoDefault(False)
    cancel_update.clicked.connect(lambda: self.update.close())
    button_w_update = QWidget()
    l2_update = QGridLayout()
    button_w_update.setLayout(l2_update)
    l2_update.addWidget(OK_update, 0,0)
    l2_update.addWidget(cancel_update, 0,2)
    master_layout_update.addWidget(button_w_update)
    self.update.setGeometry(400, 400,150,150)
    
  
  
  def __buy_stock(self):
      #prompt user with dialog box to add stock
      #symbol, ok = QInputDialog.getText(self, 'Add a stock', 'Enter symbol:')
      self.buy.close()
      symbol = self.symbol_choice.text()
      amount = self.share_choice.text()
      price = self.price.text()
      if symbol != '' and amount != '' and price != '':
        name = self.__symbols.get(symbol)
        self.__portfolio.buy_stock(symbol,float(amount),float(price),name)
        self.symbol_choice.clear()
        self.share_choice.clear()
        self.__repop_stocks()
        
    
  def __repop_stocks(self):
  	  symbols = self.__portfolio.get_symbols()
  	  self.win_panel.clear()
  	  for item in symbols:
  	    self.win_panel.addItem(item)
  	  self.disp_panel.clear()
  	  self.disp_panel.setText('Total Portfolio Value:\n\n' + str(self.__portfolio.get_total_value()))
  	  print(self.__portfolio.get_symbols())
  	  self.sell_choice.clear()
  	  self.sell_choice.addItems(self.__portfolio.get_symbols())
  	  self.update_choice.clear()
  	  self.update_choice.addItems(self.__portfolio.get_symbols())
     
    
  def __sell_stock(self):
      self.sell.close()
      #print(self.share_choice.text())
      self.__portfolio.sell_stock(self.sell_choice.currentText(), float(self.share_sell_choice.text()))
      self.share_sell_choice.clear()
      self.__repop_stocks()
  
  def __update_price(self):
    self.update.close()
    self.__portfolio.update_price(self.update_choice.currentText(), float(self.update_price_choice.text()))
    self.update_price_choice.clear()
    self.__repop_stocks()
    
  def display_info(self):
    symbol = self.win_panel.currentItem().text()
    info = self.__portfolio.get_stock_info(symbol)
    self.panel.setText('Full name: ' + str(info[0])+ '\n\nShares held: ' + str(info[1])\
     + '\n\n' + 'Last price: ' + str(info[2]))


  	
if __name__ == '__main__':
  app = QApplication(sys.argv)
  #Create a portfolio object called my_port - fill in the right hand side of the assignment statement below
  my_port  = #fill in
  pv = Portfolio_Viewer(my_port)
  pv.show()
  sys.exit(app.exec_())
