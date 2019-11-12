import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookApplicationSharedModule } from 'app/shared';
import {
  BoekMySuffixComponent,
  BoekMySuffixDetailComponent,
  BoekMySuffixUpdateComponent,
  BoekMySuffixDeletePopupComponent,
  BoekMySuffixDeleteDialogComponent,
  boekRoute,
  boekPopupRoute
} from './';

const ENTITY_STATES = [...boekRoute, ...boekPopupRoute];

@NgModule({
  imports: [BookApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BoekMySuffixComponent,
    BoekMySuffixDetailComponent,
    BoekMySuffixUpdateComponent,
    BoekMySuffixDeleteDialogComponent,
    BoekMySuffixDeletePopupComponent
  ],
  entryComponents: [
    BoekMySuffixComponent,
    BoekMySuffixUpdateComponent,
    BoekMySuffixDeleteDialogComponent,
    BoekMySuffixDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookApplicationBoekMySuffixModule {}
