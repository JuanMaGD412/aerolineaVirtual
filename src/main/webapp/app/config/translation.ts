import { Storage, TranslatorContext } from 'react-jhipster';

import { setLocale } from 'app/shared/reducers/locale';

TranslatorContext.setDefaultLocale('es');
TranslatorContext.setRenderInnerTextForMissingKeys(false);

export const languages: any = {
  es: { name: 'Español' },
  en: { name: 'English' },
  'pt-br': { name: 'Português (Brasil)' },
  // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
};

export const locales = Object.keys(languages).sort();

export const registerLocale = store => {
  store.dispatch(setLocale(Storage.session.get('locale', 'es')));
};
