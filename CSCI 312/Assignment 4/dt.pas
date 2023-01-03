program dt (output);

  uses
    sysutils;

  //set a record for the date type
  type
    date_t = record
    month: 1..12;
    day: 1..31;
    year: integer;
    end;
    month_range = 1..12;
    day_range = 1..31;

  var
    d1, d2, d3 : date_t;
    format_str : string;


  //set the date according to the given variables
  procedure init_date (var dt : date_t; day : day_range; month : month_range; year : integer);
  begin
    dt.month := month;
    dt.day := day;
    dt.year := year;
  end;

  //initialize the date to the current date
  procedure init_date1 (var dt : date_t);
  var
    month, day, year : word;

  begin
    DecodeDate (Date, year, month, day);
    dt.month := month;
    dt.day := day;
    dt.year := year;
  end;

  //return true if the given dates are the same, otherwise return false
  function date_equal (date1 : date_t; date2 : date_t) : boolean;
  begin
    if ((date1.day = date2.day) and (date1.month = date2.month) and (date1.year = date2.year)) then
      date_equal := true
    else
      date_equal := false;
  end;

  //return true if the first given date is less than the second given date
  function date_less_than (date1 : date_t; date2 : date_t) : boolean;
    begin
    //check the date1 year first to see if it is smaller
    //if the year is greater the program will pass the if blocks and return false
    if (date1.year < date2.year) then
      date_less_than := true
    //if the years are equal, check to see if the date 1 month is smaller
    else if ((date1.year = date2.year) and (date1.month < date2.month)) then
      date_less_than := true
    //if the years and months are equal, check if the date 1 day is smaller
    else if((date1.year = date2.year) and (date1.month = date2.month) and (date1.day < date2.day)) then
      date_less_than := true
    //if date1 is larger than date2, the program will return false
    else
      date_less_than := false;
    end;

  //return the correct string corresponding to the month number
  function month_str (month : month_range) : string;
  begin
    case (month) of
    1 : month_str := 'January';
    2 : month_str := 'February';
    3 : month_str := 'March';
    4 : month_str := 'April';
    5 : month_str := 'May';
    6 : month_str := 'June';
    7 : month_str := 'July';
    8 : month_str := 'August';
    9 : month_str := 'September';
    10 : month_str := 'October';
    11 : month_str := 'November';
    12 : month_str := 'December';
  end;
  end;

  //set the variable given to a correctly formatted date
  //uses a pointer and returns nothing
  //produces a string in the format 'month day, year'
  procedure format_date (dt : date_t; var ret_str : string);
  begin
    ret_str := (month_str(dt.month) + ' ' + inttostr(dt.day) + ', ' + inttostr(dt.year));
  end;

  //sets the given date to the next day
  procedure next_day (var dt : date_t);

    //cheks to see if the given year is a leap year
    function leap_year (year : integer) : boolean;
    begin
      //first checks to see if the year is divisible by 4
      if ((year mod 4) = 0) then
      begin
        // if the year is divisible by 4, check if it is also divisible by 100
        //if the year isn't divisible by 100, then it will be a leap year
        if ((year mod 100) = 0) then
        begin
          //if the year is divisible by 100, it is only a leap year if it is also divisible by 400
          if ((year mod 400) = 0) then
            leap_year := true
          else
            leap_year := false;
        end
        else
          leap_year := true;
      end
      else
        leap_year := false;

    end;

    //returns the length of month given
    function month_length (month : month_range; leap : boolean): day_range;
    begin
      case (month) of
      1 : month_length := 31;
      //check to see if the year is a leap year and then return the correct number of days
      2 : begin
            if (leap = true) then
              month_length := 29
            else
              month_length := 28;
          end;
      3 : month_length := 31;
      4 : month_length := 30;
      5 : month_length := 31;
      6 : month_length := 30;
      7 : month_length := 31;
      8 : month_length := 31;
      9 : month_length := 30;
      10 : month_length := 31;
      11 : month_length := 30;
      12 : month_length := 31;
      end;
    end;

  begin
    //check to see if the current day is at the end of the month
    if (dt.day < month_length(dt.month, leap_year(dt.year))) then
      dt.day := dt.day + 1
    else
    begin
      //if the day is at the end of the month, switch to the first of the next month and update the year if neccessary
      dt.day := 1;
      if (dt.month = 12) then
      begin
        dt.month := 1;
        dt.year := dt.year + 1;
      end
      else
        dt.month := dt.month + 1
    end;
  end;

  //prints out dates and the retrun values of various functions
  begin
  init_date1(d1);
  init_date(d2, 30, 12, 1999);
  init_date(d3, 1, 1, 2000);
  format_date(d1, format_str);
  writeln('d1: ' + format_str);
  format_date(d2, format_str);
  writeln('d2: ' + format_str);
  format_date(d3, format_str);
  writeln('d3: ' + format_str);
  writeln();

  write('d1 < d2? ');
  writeln(date_less_than(d1, d2));
  write('d2 < d3? ');
  writeln(date_less_than(d2, d3));
  writeln();

  next_day(d2);
  format_date(d2, format_str);
  writeln('next day d2: ' + format_str);
  write('d2 < d3? ');
  writeln(date_less_than(d2, d3));
  write('d2 = d3? ');
  writeln(date_equal(d2, d3));
  write('d2 > d3? ');
  //checks to see if the given date is greater
  //if equals and less than functions are false, then greater than is true
  if (date_less_than(d2, d3) or date_equal(d2, d3)) then
    writeln('FALSE')
  else
    writeln('TRUE');
  writeln();

  next_day(d2);
  format_date(d2, format_str);
  writeln('next day d2: ' + format_str);
  write('d2 = d3? ');
  writeln(date_equal(d2, d3));
  writeln();

  init_date(d1, 28, 2, 1529);
  format_date(d1, format_str);
  writeln('initialized d1 to ' + format_str);
  next_day(d1);
  format_date(d1, format_str);
  writeln('next day d1: ' + format_str);
  writeln();

  init_date(d1, 28, 2, 1460);
  format_date(d1, format_str);
  writeln('initialized d1 to ' + format_str);
  next_day(d1);
  format_date(d1, format_str);
  writeln('next day d1: ' + format_str);
  writeln();

  init_date(d1, 28, 2, 1700);
  format_date(d1, format_str);
  writeln('initialized d1 to ' + format_str);
  next_day(d1);
  format_date(d1, format_str);
  writeln('next day d1: ' + format_str);
  writeln();

  init_date(d1, 28, 2, 1600);
  format_date(d1, format_str);
  writeln('initialized d1 to ' + format_str);
  next_day(d1);
  format_date(d1, format_str);
  writeln('next day d1: ' + format_str);

  end.
