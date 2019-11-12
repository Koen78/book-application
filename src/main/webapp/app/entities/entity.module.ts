import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'persoon-my-suffix',
        loadChildren: () => import('./persoon-my-suffix/persoon-my-suffix.module').then(m => m.BookApplicationPersoonMySuffixModule)
      },
      {
        path: 'boek-my-suffix',
        loadChildren: () => import('./boek-my-suffix/boek-my-suffix.module').then(m => m.BookApplicationBoekMySuffixModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookApplicationEntityModule {}
