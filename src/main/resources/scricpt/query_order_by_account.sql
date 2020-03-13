select o1.id              as order_id,
       o1.account_id      as account_id,
       o1.create_time     as create_time,
       o1.finish_time     as finish_time,
       o1.actual_amount   as actual_amount,
       o1.total_money     as total_money,
       o1.order_status    as order_status,
       o1.account_name    as account_name,
       o2.id              as item_id,
       o2.goods_id        as goods_id,
       o2.goods_name      as goods_name,
       o2.goods_introduce as goods_introduce,
       o2.goods_num       as goods_num,
       o2.goods_unit      as goods_unit,
       o2.goods_price     as goods_price,
       o2.goods_discount  as goods_discount
from `order` as o1
         left join order_item as o2 on
          o1.id = o2.order_id
where o1.account_id = ? order by order_id;