import 'Frontend/demo/init'; // hidden-source-line

import { css, html, LitElement } from 'lit';
import { customElement, state } from 'lit/decorators.js';
import '@vaadin/multi-select-combo-box';
import { getCountries } from 'Frontend/demo/domain/DataService';
import Country from 'Frontend/generated/com/vaadin/demo/domain/Country';
import { applyTheme } from 'Frontend/generated/theme';
import { MultiSelectComboBoxI18n } from '@vaadin/multi-select-combo-box';

@customElement('multi-select-combo-box-basic')
export class Example extends LitElement {
  static get styles() {
    return css`
      vaadin-multi-select-combo-box {
        width: 300px;
      }
    `;
  }

  protected createRenderRoot() {
    const root = super.createRenderRoot();
    // Apply custom theme (only supported if your app uses one)
    applyTheme(root);
    return root;
  }

  @state()
  private items: Country[] = [];

  async firstUpdated() {
    this.items = await getCountries();
  }

  // tag::snippet[]
  private i18n: MultiSelectComboBoxI18n = {
    cleared: 'Alle Einträge entfernt',
    focused: ' ausgewählt. Drücke Rücktaste zum Entfernen',
    selected: ' hinzugefügt',
    deselected: ' entfernt',
    total: '{count} Einträge ausgewählt',
  };

  render() {
    return html`
      <vaadin-multi-select-combo-box
        label="Länder"
        item-label-path="name"
        item-id-path="id"
        .items="${this.items}"
        .i18n="${this.i18n}"
      ></vaadin-multi-select-combo-box>
    `;
  }
  // end::snippet[]
}
