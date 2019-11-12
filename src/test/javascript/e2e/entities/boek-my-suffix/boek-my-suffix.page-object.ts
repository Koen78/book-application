import { element, by, ElementFinder } from 'protractor';

export class BoekComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-boek-my-suffix div table .btn-danger'));
  title = element.all(by.css('jhi-boek-my-suffix div h2#page-heading span')).first();

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

export class BoekUpdatePage {
  pageTitle = element(by.id('jhi-boek-my-suffix-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  titelInput = element(by.id('field_titel'));
  auteurInput = element(by.id('field_auteur'));
  paginasInput = element(by.id('field_paginas'));
  korteInhoudInput = element(by.id('field_korteInhoud'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setTitelInput(titel) {
    await this.titelInput.sendKeys(titel);
  }

  async getTitelInput() {
    return await this.titelInput.getAttribute('value');
  }

  async setAuteurInput(auteur) {
    await this.auteurInput.sendKeys(auteur);
  }

  async getAuteurInput() {
    return await this.auteurInput.getAttribute('value');
  }

  async setPaginasInput(paginas) {
    await this.paginasInput.sendKeys(paginas);
  }

  async getPaginasInput() {
    return await this.paginasInput.getAttribute('value');
  }

  async setKorteInhoudInput(korteInhoud) {
    await this.korteInhoudInput.sendKeys(korteInhoud);
  }

  async getKorteInhoudInput() {
    return await this.korteInhoudInput.getAttribute('value');
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

export class BoekDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-boek-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-boek'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
