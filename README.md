Respondan en pareja, en una hoja o en el chat del curso:

¿Qué le pasa a Pedidos cuando Catálogo no está? ¿Se cae también?
    
    Pedidos está funcional, pero como está acoplado a la funcionalidad de la que tiene responsabilidad Catálogo, no puede solucionar la request y retorna un error 500 (error de parte del servidor, no del cliente). No se cae, sigue funcionando, y en el caso de que tenga otras funcionalidades que no dependan de catalogo, las seguirá ofreciendo.
    
¿Qué pasaría si Catálogo no estuviera caído, sino muy lento?

    

Pedidos no descuenta el stock de Catálogo. ¿Quién debería hacerlo y qué pasa si falla después de guardar el pedido?
