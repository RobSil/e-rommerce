# e-rommerce

This "e-commerce" should carry out next requirements:
 - for now "dummy payments" and checkouts
 - inventory/warehouse management
 - goods management

[//]: # ( - direct chat client with support?)

Just some current tech doc:
 - Category. Service as a collection for products, and can be nested.
 - Product.
 - Order. Creates order, reserving product for a while. If user doesn't pay for order, give back quantity of product.

[//]: # ( - Group and GroupOptions. They are intended to show some variations in general of the same product, but may be different specs.)
[//]: # ( - ProductFilter &#40;category related&#41; and ProductFilterOption. So, this is actually intended for filtering of product &#40;i.e. processors: amount of cores, amount of threads, core frequency, manufacturer, etc.&#41;)
