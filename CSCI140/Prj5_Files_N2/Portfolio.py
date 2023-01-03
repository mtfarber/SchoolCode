import pandas as pd

class Portfolio:
    
    def __init__(self, name, filename = None): #Code is correct, DO NOT CHANGE ANYTHING unless you are completing extra credit
        self.__name = name
        #Data frame to hold portfolio, index is Symbol
        self.__holdings = pd.DataFrame(columns = ['Symbol','Name', 'Shares', 'Last Price'])
        self.__holdings.set_index('Symbol', inplace = True)
        #Set up dictionary with symbols and names
        nasdaq = pd.read_csv('nasdaq_symbol_info.csv')
        nasdaq['Security Name'] = nasdaq['Security Name'].str.replace('Common Stock', '').str.rstrip(' -')
        self.symbol_map = dict(zip(nasdaq['Symbol'], nasdaq['Security Name']))
    
    def __is_held(self, symbol): #Code is correct, DO NOT CHANGE ANYTHING in the def line or indented code
        return symbol in self.__holdings.index #Do not change
        
    def buy_stock(self, symbol, shares, price, name = None):#Fill in your code, do not change def line
        if self.__is_held(symbol):
            self.__holdings.loc[symbol, 'Shares'] += shares
            self.__holdings.loc[symbol, 'Last Price'] = price
            if pd.isnull(self.__holdings.loc[symbol, 'Name']):
                if name != None:
                    self.__holdings.loc[symbol, 'Name'] = name
        else:
            if name != None:
                self.__holdings.loc[symbol] = [name, shares, price]
            elif symbol in self.symbol_map.keys():
                self.__holdings.loc[symbol] = [self.symbol_map[symbol], shares, price]
            else:
                self.__holdings.loc[symbol] = [name, shares, price]
    
    def sell_stock(self, symbol, shares): #Fill in your code, do not change def line
        if self.__is_held(symbol):
            current_shares = self.__holdings.loc[symbol, 'Shares']
            if current_shares > shares:
                new_shares = current_shares-shares
                self.__holdings.loc[symbol, 'Shares'] = new_shares
            if current_shares == shares:
                self.__holdings.drop(symbol, inplace = True)
            if current_shares < shares:
                print('Amount to sell is more than amount of shares held.')
        else:
            print('Stock is not held in the portfolio.')
    
    def update_price(self, symbol, price): #Fill in your code, do not change def line
        if self.__is_held(symbol):
            self.__holdings.loc[symbol, 'Last Price'] = price
    
    def get_total_value(self): #Fill in your code, do not change def line
        self.__holdings['Products'] = self.__holdings['Shares'] * self.__holdings['Last Price']
        total = self.__holdings['Products'].agg('sum')
        del self.__holdings['Products']
        return total
    
    def get_stock_info(self, symbol): #Fill in your code, do not change def line
        if self.__is_held(symbol):
            return [self.__holdings.loc[symbol, 'Name'],self.__holdings.loc[symbol, 'Shares'],self.__holdings.loc[symbol, 'Last Price']]
    
    #You won't use the two accessor methods below in your other methods,but they are examples of accessors
    #They are needed by the GUI - don't change them
    def get_symbols(self): #Code is correct, DO NOT CHANGE ANYTHING in the def line or indented code
        return list(self.__holdings.index) #Do not change
    
    def get_name(self): #Code is correct, DO NOT CHANGE ANYTHING in the def line or indented code
        return self.__name #Do not change
    
    def save_to_file(self, filename): #Fill in your code for EXTRA CREDIT below, do not change def line
        pass
        
    def __str__(self): #Do not change def line, debug the line of code below
        return (self.__name + "'s Portfolio:\n"  + str(self.__holdings)) #Correct this line - don't add lines