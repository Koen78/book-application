import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookApplicationSharedModule } from 'app/shared';
import {
  PersoonMySuffixComponent,
  PersoonMySuffixDetailComponent,
  PersoonMySuffixUpdateComponent,
  PersoonMySuffixDeletePopupComponent,
  PersoonMySuffixDeleteDialogComponent,
  persoonRoute,
  persoonPopupRoute
} from './';

const ENTITY_STATES = [...persoonRoute, ...persoonPopupRoute];

@NgModule({
  imports: [BookApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PersoonMySuffixComponent,
    PersoonMySuffixDetailComponent,
    PersoonMySuffixUpdateComponent,
    PersoonMySuffixDeleteDialogComponent,
    PersoonMySuffixDeletePopupComponent
  ],
  entryComponents: [
    PersoonMySuffixComponent,
    PersoonMySuffixUpdateComponent,
    PersoonMySuffixDeleteDialogComponent,
    PersoonMySuffixDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookApplicationPersoonMySuffixModule {}
