import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BookApplicationSharedLibsModule, BookApplicationSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [BookApplicationSharedLibsModule, BookApplicationSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [BookApplicationSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookApplicationSharedModule {
  static forRoot() {
    return {
      ngModule: BookApplicationSharedModule
    };
  }
}
