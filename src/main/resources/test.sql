SELECT c.country_id
FROM movie.country c
WHERE c.country LIKE 'Slovakia';

SELECT c.country_id
FROM movie.country c
WHERE c.country LIKE 'Lost Country';

SELECT ci.city_id, ci.country_id
FROM movie.city ci JOIN movie.country co on co.country_id = ci.country_id
WHERE ci.city LIKE 'Zaria' AND co.country LIKE 'Nigeria';

SELECT ci.city_id, ci.country_id
FROM movie.city ci JOIN movie.country co on co.country_id = ci.country_id
WHERE ci.city LIKE 'SinCity' AND co.country LIKE 'Lost Country';

SELECT a.address_id, a.city_id, ci.country_id
FROM movie.address a JOIN movie.city ci on a.city_id = ci.city_id
JOIN movie.country co on ci.country_id = co.country_id
WHERE a.address LIKE '1531 Sal Drive' AND a.phone LIKE '648856936185'; -- 14/162/46

SELECT cu.customer_id, cu.address_id, a.city_id, ci.country_id
FROM movie.customer cu JOIN movie.address a on cu.address_id = a.address_id
JOIN  movie.city ci on a.city_id = ci.city_id
JOIN movie.country co on ci.country_id = co.country_id
WHERE cu.first_name LIKE 'JOANN' AND cu.last_name LIKE 'GARDNER'
  AND cu.email LIKE 'JOANN.GARDNER@sakilacustomer.org' AND cu.store_id = 2; -- 164/168/529/97

SELECT cu.customer_id, cu.store_id, r.rental_id
FROM movie.customer cu JOIN rental r on cu.customer_id = r.customer_id
WHERE cu.first_name LIKE 'DWAYNE' AND cu.last_name LIKE 'OLVERA'
  AND cu.email LIKE 'DWAYNE.OLVERA@sakilacustomer.org' AND r.return_date IS NULL; -- 554/1/14098