"use client"

import { useState } from "react"
import { Menu } from "lucide-react"
import { Button } from "@/components/ui/button"
import Header from "./header"
import Sidebar from "./sidebar"
import Footer from "./footer"

export default function Dashboard() {
  const [sidebarOpen, setSidebarOpen] = useState(false)

  return (
    <div className="flex min-h-screen flex-col">
      <Header>
        <Button variant="ghost" size="icon" className="md:hidden" onClick={() => setSidebarOpen(!sidebarOpen)}>
          <Menu className="h-6 w-6" />
          <span className="sr-only">Toggle navigation menu</span>
        </Button>
      </Header>

      <div className="flex flex-1 overflow-hidden">
        <Sidebar open={sidebarOpen} setOpen={setSidebarOpen} />

        <main className="flex-1 overflow-y-auto p-4 md:p-6">
          <div className="rounded-lg border border-border bg-card p-6 shadow-sm">
            <h1 className="mb-6 text-2xl font-bold">Dashboard Overview</h1>
            <p className="text-muted-foreground">
              Welcome to your admin dashboard. Select an option from the sidebar to manage your content.
            </p>

            {/* Placeholder content */}
            <div className="mt-8 grid gap-4 md:grid-cols-2 lg:grid-cols-3">
              {[1, 2, 3, 4, 5, 6].map((i) => (
                <div key={i} className="rounded-md border border-border bg-background p-4 shadow-sm">
                  <div className="h-24 animate-pulse rounded-md bg-muted"></div>
                  <div className="mt-3 h-4 w-3/4 animate-pulse rounded-md bg-muted"></div>
                  <div className="mt-2 h-3 animate-pulse rounded-md bg-muted"></div>
                </div>
              ))}
            </div>
          </div>
        </main>
      </div>

      <Footer />
    </div>
  )
}
