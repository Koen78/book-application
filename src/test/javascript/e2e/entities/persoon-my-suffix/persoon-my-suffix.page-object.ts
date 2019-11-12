import { element, by, ElementFinder } from 'protractor';

export class PersoonComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-persoon-my-suffix div table .btn-danger'));
  title = element.all(by.css('jhi-persoon-my-suffix div h2#page-heading span')).first();

  async clickOnCreateButton() {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton() {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getText();
  }
}

export class PersoonUpdatePage {
  pageTitle = element(by.id('jhi-persoon-my-suffix-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  naamInput = element(by.id('field_naam'));
  voornaamInput = element(by.id('field_voornaam'));
  wenslijstSelect = element(by.id('field_wenslijst'));
  boekenlijstSelect = element(by.id('field_boekenlijst'));
  gelezenSelect = element(by.id('field_gelezen'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNaamInput(naam) {
    await this.naamInput.sendKeys(naam);
  }

  async getNaamInput() {
    return await this.naamInput.getAttribute('value');
  }

  async setVoornaamInput(voornaam) {
    await this.voornaamInput.sendKeys(voornaam);
  }

  async getVoornaamInput() {
    return await this.voornaamInput.getAttribute('value');
  }

  async wenslijstSelectLastOption() {
    await this.wenslijstSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async wenslijstSelectOption(option) {
    await this.wenslijstSelect.sendKeys(option);
  }

  getWenslijstSelect(): ElementFinder {
    return this.wenslijstSelect;
  }

  async getWenslijstSelectedOption() {
    return await this.wenslijstSelect.element(by.css('option:checked')).getText();
  }

  async boekenlijstSelectLastOption() {
    await this.boekenlijstSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async boekenlijstSelectOption(option) {
    await this.boekenlijstSelect.sendKeys(option);
  }

  getBoekenlijstSelect(): ElementFinder {
    return this.boekenlijstSelect;
  }

  async getBoekenlijstSelectedOption() {
    return await this.boekenlijstSelect.element(by.css('option:checked')).getText();
  }

  async gelezenSelectLastOption() {
    await this.gelezenSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async gelezenSelectOption(option) {
    await this.gelezenSelect.sendKeys(option);
  }

  getGelezenSelect(): ElementFinder {
    return this.gelezenSelect;
  }

  async getGelezenSelectedOption() {
    return await this.gelezenSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class PersoonDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-persoon-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-persoon'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
