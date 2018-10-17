select l.line_no, ls.veh_time, group_concat(s.name order by ls.sequence) from line_station ls left join station_no_repeat s on ls.station_id = s.id, line l
where l.line_id = ls.line_id and ls.line_id in 
(972,81333,976,973,975,974,971,970,1539,1536,1538,1537,4355,4356,4363,4364,4378,4379,4380,4381,4384,4385,5409,5410,5412,5745,5746,5747,5748,6801,6802,7094,
7095,7620,7621,7655,7656,128630,7863,7864,32118,8965,8966,14967,11128,11129,11895,15170,12744,12745,14950,14951,14542,14543,17242,17243,16657,44073,44074,
45368,45369,59945,48887,48888,81331,56976,56977,59602,59603,67022,66420,66421,136499,81747,81749,81754,81755,81757,81759,112336,111602,111603,128789,111600,
111601,111598,111599,112930,112931,113786,115650,115653,138064,138065)
group by ls.line_id order by ls.veh_time

-- select distinct(line_id) from line_station;
-- select count(*) from line_station;
-- select distinct(id) from station where name like '%东城国际%'

/*
(select l.line_no as lno, l.on_station_name as lonname, l.off_station_name as loffname,ls.veh_time as lvtime -- , s.name
from line_station ls 
	left join line l on ls.line_id = l.line_id 
	-- left join station s on ls.station_id = s.id 
where ls.station_id in (553706,5197,5198,553711,553713,553548,553724,553727,553734,553703,553737,553776)
-- group by l.line_no
order by ls.veh_time)
*/

-- select ls.line_id from line_station ls 
-- where ls.station_id in (553706,5197,5198,553711,553713,553548,553724,553727,553734,553703,553737,553776)

-- select distinct(id) from station;

 -- select count(id) from station;

-- select * from station where name ='' 

insert into station_no_repeat (select * from station group by id);