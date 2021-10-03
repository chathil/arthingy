//
//  WikipixApp.swift
//  Wikipix
//
//  Created by Abdul Chathil on 05/08/21.
//

import SwiftUI
import wikipix

@main
struct WikipixApp: App {
  let sdk = WikipixSdk(databaseDriverFactory: DatabaseDriverFactory(), log: Kermit.init(logger: NSLogLogger()))
    var body: some Scene {
      WindowGroup {
          ContentView(viewModel: ViewModel(useCase: sdk.provideUseCase()))
        }
    }
}
