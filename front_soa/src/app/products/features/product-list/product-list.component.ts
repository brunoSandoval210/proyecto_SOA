import { Component,/*, inject */
inject} from '@angular/core';
import { ProductsStateService } from '../../data-access/product-state.service';
//import { ProductsService } from '../../data-access/products.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [],
  templateUrl: './product-list.component.html',
  styles: ``,
  providers: [ProductsStateService],
})
export default class ProductListComponent {
  // private productsService = inject(ProductsService);

  // constructor(){
  //   this.productsService.getProducts().subscribe((products) => {
  //     console.log(products);
  //   })
  // }

  productsState= inject(ProductsStateService);

}
