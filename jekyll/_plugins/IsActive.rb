module Jekyll
  class IsActive < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @section = text
      @section = @section.start_with?("/") ? @section : context[@section]
    end

    def render(context)
      context.registers[:page]['url'] == @section ? "active" : ""
    end
  end
end

Liquid::Template.register_tag('is_active', Jekyll::IsActive)

module Jekyll
  class IsActiveLevel < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @section = text
    end

    def render(context)
      context.registers[:page]['url'].start_with?(@section.start_with?("/") ? @section : context[@section]) ? "active" : ""
    end
  end
end

Liquid::Template.register_tag('is_active_level', Jekyll::IsActiveLevel)

module Jekyll
  class IfActiveLevelSetIn < Liquid::Tag

    def initialize(tag_name, text, tokens)
      super
      @section = text
    end

    def render(context)
      context.registers[:page]['url'].start_with?(@section.start_with?("/") ? @section : context[@section]) ? "in" : ""
    end
  end
end

Liquid::Template.register_tag('if_active_level_set_in', Jekyll::IfActiveLevelSetIn)
