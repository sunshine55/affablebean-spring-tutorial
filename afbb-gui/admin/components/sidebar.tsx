"use client"

import { useEffect } from "react"
import Link from "next/link"
import { ChevronDown, LayoutDashboard, ShoppingBasket, Tags, Users, X } from "lucide-react"
import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import { Collapsible, CollapsibleContent, CollapsibleTrigger } from "@/components/ui/collapsible"

interface SidebarProps {
  open: boolean
  setOpen: (open: boolean) => void
}

export default function Sidebar({ open, setOpen }: SidebarProps) {
  // Close sidebar when clicking outside on mobile
  useEffect(() => {
    const handleResize = () => {
      if (window.innerWidth >= 768) {
        setOpen(false)
      }
    }

    window.addEventListener("resize", handleResize)
    return () => window.removeEventListener("resize", handleResize)
  }, [setOpen])

  return (
    <>
      {/* Mobile overlay */}
      {open && (
        <div
          className="fixed inset-0 z-40 bg-background/80 backdrop-blur-sm md:hidden"
          onClick={() => setOpen(false)}
        />
      )}

      {/* Sidebar */}
      <div
        className={cn(
          "fixed inset-y-0 left-0 z-50 w-64 transform border-r bg-background transition-transform duration-200 ease-in-out md:static md:translate-x-0",
          open ? "translate-x-0" : "-translate-x-full",
        )}
      >
        <div className="flex h-16 items-center justify-between border-b px-4 md:hidden">
          <span className="text-lg font-bold">Menu</span>
          <Button variant="ghost" size="icon" onClick={() => setOpen(false)}>
            <X className="h-5 w-5" />
            <span className="sr-only">Close sidebar</span>
          </Button>
        </div>

        <nav className="flex flex-col gap-2 p-4">
          <Link
            href="#"
            className="flex items-center gap-2 rounded-md px-3 py-2 text-sm font-medium hover:bg-accent hover:text-accent-foreground"
          >
            <LayoutDashboard className="h-4 w-4" />
            Dashboard
          </Link>

          <Collapsible className="w-full">
            <CollapsibleTrigger className="flex w-full items-center justify-between rounded-md px-3 py-2 text-sm font-medium hover:bg-accent hover:text-accent-foreground">
              <div className="flex items-center gap-2">
                <Tags className="h-4 w-4" />
                Category Management
              </div>
              <ChevronDown className="h-4 w-4 transition-transform ui-open:rotate-180" />
            </CollapsibleTrigger>
            <CollapsibleContent>
              <div className="ml-6 mt-1 flex flex-col gap-1">
                <Link href="#" className="rounded-md px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground">
                  All Categories
                </Link>
                <Link href="#" className="rounded-md px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground">
                  Add New Category
                </Link>
              </div>
            </CollapsibleContent>
          </Collapsible>

          <Collapsible className="w-full">
            <CollapsibleTrigger className="flex w-full items-center justify-between rounded-md px-3 py-2 text-sm font-medium hover:bg-accent hover:text-accent-foreground">
              <div className="flex items-center gap-2">
                <ShoppingBasket className="h-4 w-4" />
                Items Management
              </div>
              <ChevronDown className="h-4 w-4 transition-transform ui-open:rotate-180" />
            </CollapsibleTrigger>
            <CollapsibleContent>
              <div className="ml-6 mt-1 flex flex-col gap-1">
                <Link href="#" className="rounded-md px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground">
                  All Items
                </Link>
                <Link href="#" className="rounded-md px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground">
                  Add New Item
                </Link>
                <Link href="#" className="rounded-md px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground">
                  Inventory
                </Link>
              </div>
            </CollapsibleContent>
          </Collapsible>

          <Collapsible className="w-full">
            <CollapsibleTrigger className="flex w-full items-center justify-between rounded-md px-3 py-2 text-sm font-medium hover:bg-accent hover:text-accent-foreground">
              <div className="flex items-center gap-2">
                <Users className="h-4 w-4" />
                User Management
              </div>
              <ChevronDown className="h-4 w-4 transition-transform ui-open:rotate-180" />
            </CollapsibleTrigger>
            <CollapsibleContent>
              <div className="ml-6 mt-1 flex flex-col gap-1">
                <Link href="#" className="rounded-md px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground">
                  All Users
                </Link>
                <Link href="#" className="rounded-md px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground">
                  Add New User
                </Link>
                <Link href="#" className="rounded-md px-3 py-2 text-sm hover:bg-accent hover:text-accent-foreground">
                  Roles & Permissions
                </Link>
              </div>
            </CollapsibleContent>
          </Collapsible>
        </nav>
      </div>
    </>
  )
}
